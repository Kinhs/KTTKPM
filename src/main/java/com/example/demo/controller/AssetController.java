package com.example.demo.controller;

import com.example.demo.model.Asset;
import com.example.demo.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable Long id) {
        Optional<Asset> asset = assetService.getAssetById(id);
        return asset.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<Asset>> getAssetsByRoom(@PathVariable Long roomId) {
        List<Asset> assets = assetService.getAssetsByRoom(roomId);
        return assets != null ? ResponseEntity.ok(assets) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Asset> createAsset(@RequestBody Asset asset) {
        return new ResponseEntity<>(assetService.saveAsset(asset), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable Long id, @RequestBody Asset asset) {
        Optional<Asset> existingAsset = assetService.getAssetById(id);
        if (existingAsset.isPresent()) {
            asset.setId(id);
            return ResponseEntity.ok(assetService.saveAsset(asset));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsset(@PathVariable Long id) {
        Optional<Asset> existingAsset = assetService.getAssetById(id);
        if (existingAsset.isPresent()) {
            assetService.deleteAsset(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


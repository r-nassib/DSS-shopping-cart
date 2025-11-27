package com.cart.cart.controller;

import com.cart.cart.services.DatabaseExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private DatabaseExportService databaseExportService;

    @GetMapping("/admin/export")
    public ResponseEntity<byte[]> exportDatabase() {
        String sql = databaseExportService.exportDatabaseToSql();
        byte[] content = sql.getBytes();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"products.sql\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(content);
    }
}
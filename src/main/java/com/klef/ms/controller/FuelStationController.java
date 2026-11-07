
package com.klef.ms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.ms.entity.FuelStation;
import com.klef.ms.service.FuelStationService;

@RestController
@RequestMapping("/fuel")
public class FuelStationController {

    private final FuelStationService service;

    // Constructor Injection
    public FuelStationController(FuelStationService service) {
        this.service = service;
    }

    // 1. Display All Records
    @GetMapping("/all")
    public ResponseEntity<?> getall() {

        List<FuelStation> list = service.display();

        if (!list.isEmpty()) {
            return ResponseEntity.status(200).body(list);
        } else {
            return ResponseEntity.status(404).body("No Records Found");
        }
    }

    // 2. Insert New Record
    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody FuelStation entity) {

        FuelStation f = service.insert(entity);

        if (f != null) {
            return ResponseEntity.status(201).body(f);
        } else {
            return ResponseEntity.status(400).body("Unable to Insert Record");
        }
    }

    // 3. Update Existing Record
    @PutMapping("/modify")
    public ResponseEntity<?> update(@RequestBody FuelStation entity) {

        FuelStation f = service.update(entity);

        if (f != null) {
            return ResponseEntity.status(200).body(f);
        } else {
            return ResponseEntity.status(404).body("Not Found with Id");
        }
    }

    // 4. Delete Record
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long id) {

        String result = service.deleteById(id);

        if (result.equals("Successfully Deleted")) {
            return ResponseEntity.status(200).body(result);
        } else {
            return ResponseEntity.status(404).body(result);
        }
    }

    // 5. Display Record By ID
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {

        FuelStation f = service.displayById(id);

        if (f != null) {
            return ResponseEntity.status(200).body(f);
        } else {
            return ResponseEntity.status(404).body("Record Not Found with Id: " + id);
        }
    }

    // Display Records By Name
    @GetMapping("/getbyname/{name}")
    public ResponseEntity<List<FuelStation>> get(@PathVariable String name) {

        List<FuelStation> list = service.getbyName(name);

        return ResponseEntity.status(200).body(list);
    }

    // Display Records By Type
    @GetMapping("/getbyType/{name}")
    public ResponseEntity<List<FuelStation>> getByType(
            @PathVariable("name") String type) {

        List<FuelStation> list = service.getByType(type);

        return ResponseEntity.status(200).body(list);
    }
}

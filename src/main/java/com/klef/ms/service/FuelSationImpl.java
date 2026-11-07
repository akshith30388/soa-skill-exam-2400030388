
package com.klef.ms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.klef.ms.entity.FuelStation;
import com.klef.ms.repository.FuelStationRepository;

@Service
public class FuelSationImpl implements FuelStationService {

    private final FuelStationRepository repo;

    public FuelSationImpl(FuelStationRepository repo) {
        this.repo = repo;
    }

  
    @Override
    public FuelStation insert(FuelStation entity) {

        return repo.save(entity);
    }

    @Override
    public List<FuelStation> display() {

        return repo.findAll();
    }

    @Override
    public FuelStation displayById(Long id) {

        if (repo.existsById(id)) {
            return repo.findById(id).orElse(null);
        } else {
            return null;
        }
    }

    // 4. Delete Record
    @Override
    public String deleteById(Long id) {

        if (repo.existsById(id)) {

            repo.deleteById(id);

            return "Successfully Deleted";

        } else {

            return "Id Not Found: " + id;
        }
    }

    // 5. Update Existing Record
    @Override
    public FuelStation update(FuelStation entity) {

        if (repo.existsById(entity.getId())) {

            FuelStation f = repo.findById(entity.getId()).orElse(null);

            if (f != null) {

                f.setName(entity.getName());
                f.setLocation(entity.getLocation());
                f.setType(entity.getType());
                f.setStatus(entity.isStatus());

                return repo.save(f);
            }

        }

        return null;
    }

    // 6. Find By Name
    @Override
    public List<FuelStation> getbyName(String name) {

        return repo.findByName(name);
    }

    // 7. Find By Type
    @Override
    public List<FuelStation> getByType(String type) {

        return repo.findByType(type);
    }
}

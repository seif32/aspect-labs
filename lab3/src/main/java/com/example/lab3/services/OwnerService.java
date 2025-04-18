package com.example.lab3.services;

import com.example.lab3.dto.OwnerDTO;
import com.example.lab3.models.Owner;
import com.example.lab3.repositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {
  private final OwnerRepository ownerRepository;

  public Owner createOwner(OwnerDTO ownerDTO) {
    Owner owner = new Owner();
    owner.setName(ownerDTO.getName());
    owner.setEmail(ownerDTO.getEmail());
    owner.setPhone(ownerDTO.getPhone());
    return ownerRepository.save(owner);
  }

  public List<Owner> getAllOwners() {
    return ownerRepository.findAll();
  }

  public Owner getOwnerById(Long id) {
    return ownerRepository.findById(id).orElse(null);
  }

  public Owner updateOwner(Long id, OwnerDTO ownerDTO) {
    Owner owner = ownerRepository.findById(id).orElse(null);
    if (owner != null) {
      owner.setName(ownerDTO.getName());
      owner.setEmail(ownerDTO.getEmail());
      owner.setPhone(ownerDTO.getPhone());
      return ownerRepository.save(owner);
    }
    return null;
  }

  public void deleteOwner(Long id) {
    ownerRepository.deleteById(id);
  }
}

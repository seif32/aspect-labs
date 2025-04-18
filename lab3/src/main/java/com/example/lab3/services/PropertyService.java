package com.example.lab3.services;

import com.example.lab3.dto.PropertyDTO;
import com.example.lab3.models.Owner;
import com.example.lab3.models.Property;
import com.example.lab3.repositories.OwnerRepository;
import com.example.lab3.repositories.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {
  private final PropertyRepository propertyRepository;
  private final OwnerRepository ownerRepository;

  public Property createProperty(PropertyDTO propertyDTO) {
    Owner owner = ownerRepository.findById(propertyDTO.getOwnerId()).orElse(null);
    if (owner == null) {
      return null;
    }

    Property property = new Property();
    property.setAddress(propertyDTO.getAddress());
    property.setCity(propertyDTO.getCity());
    property.setState(propertyDTO.getState());
    property.setPrice(propertyDTO.getPrice());
    property.setOwner(owner);

    return propertyRepository.save(property);
  }

  public List<Property> getAllProperties() {
    return propertyRepository.findAll();
  }

  public Property getPropertyById(Long id) {
    return propertyRepository.findById(id).orElse(null);
  }

  public List<Property> getPropertiesByOwner(Long ownerId) {
    return propertyRepository.findByOwnerId(ownerId);
  }

  public Property updateProperty(Long id, PropertyDTO propertyDTO) {
    Property property = propertyRepository.findById(id).orElse(null);
    if (property != null) {
      property.setAddress(propertyDTO.getAddress());
      property.setCity(propertyDTO.getCity());
      property.setState(propertyDTO.getState());
      property.setPrice(propertyDTO.getPrice());

      if (!property.getOwner().getId().equals(propertyDTO.getOwnerId())) {
        Owner owner = ownerRepository.findById(propertyDTO.getOwnerId()).orElse(null);
        if (owner != null) {
          property.setOwner(owner);
        }
      }

      return propertyRepository.save(property);
    }
    return null;
  }

  public void deleteProperty(Long id) {
    propertyRepository.deleteById(id);
  }
}

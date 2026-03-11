package com.company.microservice_catalog.infrastructure.adapter.out.persistence.item_type;

import com.company.microservice_catalog.application.port.out.ItemTypeRepositoryPort;
import com.company.microservice_catalog.domain.model.AuditData;
import com.company.microservice_catalog.domain.model.ItemType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ItemTypePersistenceAdapter implements ItemTypeRepositoryPort {

    private final JpaItemTypeRepository jpaItemTypeRepository;

    @Override
    @Transactional
    public ItemType saveItemType(ItemType itemType) {

        ItemTypeEntity itemTypeEntity = ItemTypeEntity.builder()
                .description(itemType.getDescription())
                .status(true)
                .createAt(LocalDateTime.now())
                .createBy("ADMIN")
                .build();


        ItemTypeEntity itemTypeSaved = jpaItemTypeRepository.save(itemTypeEntity);

        return itemTypeEntityToDomain(itemTypeSaved);
    }

    @Override
    @Transactional
    public ItemType updateItemType(Long id, ItemType itemType) {

        Optional<ItemTypeEntity> itemTypeFound = jpaItemTypeRepository.findById(id);

        ItemTypeEntity itemTypeEntity = ItemTypeEntity.builder()
                .id(itemTypeFound.get().getId())
                .description(itemType.getDescription())
                .status(itemType.getStatus())
                .updateAt(LocalDateTime.now())
                .updateBy("ADMIN")
                .build();


        ItemTypeEntity itemTypeUpdated = jpaItemTypeRepository.save(itemTypeEntity);

        return itemTypeEntityToDomain(itemTypeUpdated);
    }

    @Override
    public Optional<ItemType> findItemType(Long id) {
        return jpaItemTypeRepository.findById(id)
                .map(this::itemTypeEntityToDomain);
    }

    @Override
    public List<ItemType> findAllItemsType() {
        return jpaItemTypeRepository.findAll()
                .stream()
                .filter(ItemTypeEntity::getStatus)
                .map(this::itemTypeEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteItemType(Long id) {

    }


    private AuditData toAuditData(LocalDateTime createAt, String createBy, LocalDateTime updateAt, String updateBy){
        return AuditData.builder()
                .create_at(createAt)
                .create_by(createBy)
                .update_at(updateAt)
                .update_by(updateBy)
                .build();
    }

    private ItemType itemTypeEntityToDomain(ItemTypeEntity itemTypeEntity){
        return ItemType.builder()
                .id(itemTypeEntity.getId())
                .description(itemTypeEntity.getDescription())
                .status(itemTypeEntity.getStatus())
                .audit(toAuditData(itemTypeEntity.getCreateAt(),
                        itemTypeEntity.getCreateBy(),
                        itemTypeEntity.getUpdateAt(),
                        itemTypeEntity.getUpdateBy()))
                .build();
    }


}

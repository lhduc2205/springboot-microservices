package com.lhduc.inventoryservice.seeder;

import com.lhduc.inventoryservice.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InventorySeeder {

    @Bean
    public CommandLineRunner initData(InventoryRepository inventoryRepository) {
        return args -> {
//            List<Inventory> inventories = List.of(
//                    new Inventory("iPhone X", 0),
//                    new Inventory("iPhone 13", 5),
//                    new Inventory("iPhone 13 Pro Max", 2),
//                    new Inventory("iPhone 14", 20),
//                    new Inventory("iPhone 14 Pro Max", 15)
//            );
//
//            log.info("Seeder: Initialized inventory data");
//            inventoryRepository.saveAll(inventories);
        };
    }
}

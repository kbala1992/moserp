/*******************************************************************************
 * Copyright 2013 Thomas Letsch (contact@thomas-letsch.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package org.moserp.inventory.service;

import org.moserp.common.domain.RestUri;
import org.moserp.inventory.domain.DeliveryItem;
import org.moserp.inventory.domain.InventoryItem;
import org.moserp.inventory.domain.OutgoingDelivery;
import org.moserp.inventory.repository.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class OutgoingDeliveryEventHandler {

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @HandleBeforeCreate
    public void processOutgoingDelivery(OutgoingDelivery OutgoingDelivery) {
        OutgoingDelivery.getItems().forEach(item -> bookItem(OutgoingDelivery.getFromFacility(), item));
    }

    private void bookItem(RestUri facility, DeliveryItem item) {
        InventoryItem targetInventory = inventoryItemRepository.findByProductAndFacility(item.getProductInstance(), facility);
        if (targetInventory == null) {
            targetInventory = new InventoryItem(item.getProductInstance(), facility);
        }
        targetInventory.bookOutgoing(item);
        inventoryItemRepository.save(targetInventory);
    }

}

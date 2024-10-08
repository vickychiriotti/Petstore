/**
 * Copyright 2018 SmartBear Software
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package model;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Data
@AllArgsConstructor
@Builder
@XmlRootElement(name = "Order")
public class Order {
    private long id;
    private long petId;
    private int quantity;
    private Date shipDate;
    private String status;
    private boolean complete;

    @XmlElement(name = "id")
    public long getId() {
        return id;
    }


    @XmlElement(name = "petId")
    public long getPetId() {
        return petId;
    }

    @XmlElement(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    @XmlElement(name = "status")
    public String getStatus() {
        return status;
    }

    @XmlElement(name = "shipDate")
    public Date getShipDate() {
        return shipDate;
    }

}
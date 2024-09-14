/**
 *  Copyright 2018 SmartBear Software
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "User")
public class User {
  private long id;
  private String username;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String phone;
  private int userStatus;

  @XmlElement(name = "id")
  public long getId() {
    return id;
  }

    @XmlElement(name = "firstName")
  public String getFirstName() {
    return firstName;
  }

    @XmlElement(name = "username")
  public String getUsername() {
    return username;
  }

    @XmlElement(name = "lastName")
  public String getLastName() {
    return lastName;
  }

    @XmlElement(name = "email")
  public String getEmail() {
    return email;
  }

    @XmlElement(name = "password")
  public String getPassword() {
    return password;
  }

    @XmlElement(name = "phone")
  public String getPhone() {
    return phone;
  }

    @XmlElement(name = "userStatus")
  public int getUserStatus() {
    return userStatus;
  }

}
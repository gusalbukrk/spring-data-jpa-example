package com.gusalbukrk.simpletables;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
public class User {
  @Id
  private String email;
  private String password;
}

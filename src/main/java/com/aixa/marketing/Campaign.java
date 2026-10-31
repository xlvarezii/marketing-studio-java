package com.aixa.marketing;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity public class Campaign {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 public String brand;
 public String product;
 public String platform;
 public String imageName;
 @Lob public byte[] imageBytes;
 public String imageType;
 @Column(length=2500) public String script1;
 @Column(length=2500) public String script2;
 @Column(length=2500) public String script3;
 public Integer selectedScript;
 public boolean published;
 public LocalDateTime createdAt=LocalDateTime.now();
}

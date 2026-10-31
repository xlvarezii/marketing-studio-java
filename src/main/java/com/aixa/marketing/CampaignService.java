package com.aixa.marketing;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
@Service public class CampaignService {
 private final CampaignRepository repo;
 public CampaignService(CampaignRepository repo){this.repo=repo;}
 public Campaign create(String brand,String product,String platform,MultipartFile photo) throws IOException {
  if(photo.isEmpty() || photo.getContentType()==null || !photo.getContentType().matches("image/(png|jpeg|webp)")) throw new IllegalArgumentException("Adjunta una imagen PNG, JPG o WEBP.");
  Campaign c=new Campaign();c.brand=brand.trim();c.product=product.trim();c.platform=platform;
  c.imageBytes=photo.getBytes();c.imageName=photo.getOriginalFilename();c.imageType=photo.getContentType();
  c.script1="Emocional: Descubre "+c.product+" de "+c.brand+". Una experiencia para compartir. ¡Conoce más!";
  c.script2="Beneficios: ¿Buscas algo diferente? "+c.product+" destaca por su propuesta. Escríbenos y descubre los detalles.";
  c.script3="Dinámico: ¡Atención! Llegó "+c.product+" de "+c.brand+". ¿Te animas a probarlo? Comenta y comparte.";
  return repo.save(c);
 }
 public Campaign get(Long id){return repo.findById(id).orElseThrow();}
 public void choose(Long id,int script){if(script<1||script>3)throw new IllegalArgumentException("Guion inválido");Campaign c=get(id);c.selectedScript=script;repo.save(c);}
 public void simulatePublish(Long id){Campaign c=get(id);if(c.selectedScript==null)throw new IllegalArgumentException("Selecciona un guion");c.published=true;repo.save(c);}
}

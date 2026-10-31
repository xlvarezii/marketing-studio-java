package com.aixa.marketing;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
@Controller public class WebController {
 private final CampaignRepository repo;private final CampaignService service;
 public WebController(CampaignRepository repo,CampaignService service){this.repo=repo;this.service=service;}
 @GetMapping("/") public String home(Model m){m.addAttribute("campaigns",repo.findAll());return "home";}
 @PostMapping("/campaigns") public String create(@RequestParam String brand,@RequestParam String product,@RequestParam String platform,@RequestParam MultipartFile photo,RedirectAttributes a) throws IOException {
  try {Campaign c=service.create(brand,product,platform,photo);return "redirect:/campaigns/"+c.id;}catch(IllegalArgumentException e){a.addFlashAttribute("error",e.getMessage());return "redirect:/";}
 }
 @GetMapping("/campaigns/{id}") public String detail(@PathVariable Long id,Model m){m.addAttribute("campaign",service.get(id));return "detail";}
 @GetMapping("/campaigns/{id}/image") public ResponseEntity<byte[]> image(@PathVariable Long id){Campaign c=service.get(id);return ResponseEntity.ok().contentType(MediaType.parseMediaType(c.imageType)).body(c.imageBytes);}
 @PostMapping("/campaigns/{id}/choose") public String choose(@PathVariable Long id,@RequestParam int script){service.choose(id,script);return "redirect:/campaigns/"+id;}
 @PostMapping("/campaigns/{id}/publish") public String publish(@PathVariable Long id){service.simulatePublish(id);return "redirect:/campaigns/"+id;}
}

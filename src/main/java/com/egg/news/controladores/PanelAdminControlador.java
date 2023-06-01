package com.egg.news.controladores;

import com.egg.news.errores.ErrorServicio;
import com.egg.news.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/panel")
public class PanelAdminControlador {    
    
    @Autowired
    private NoticiaServicio noticiaServicio;
            
    @GetMapping("/crear")
    public String panelAdmin(){
        
        return "panelAdmin.html";
    }
    
    @PostMapping("/crear")
    public String crear(@RequestParam String titulo, @RequestParam String cuerpo, ModelMap modelo){
        
        try {
            noticiaServicio.crear(titulo, cuerpo);
            
            modelo.put("exito", "La noticia se creó correctamente.");
        } catch (ErrorServicio ex) {
            modelo.put("error", ex.getMessage());
            return "panelAdmin.html";
        }
        
        return "redirect:../inicio";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){
        modelo.put("noticia", noticiaServicio.getOne(id));
        
        return "noticia_modificar.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String titulo, String cuerpo, ModelMap modelo){
        try {
            noticiaServicio.modificar(id, titulo, cuerpo);
            return "redirect:/inicio";
        } catch (ErrorServicio ex) {
            modelo.put("error", ex.getMessage());
            return "noticia_modificar.html";
        }        
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo) {
        try {
           modelo.put("noticia", noticiaServicio.getOne(id));
           return "noticia_eliminar.html"; 
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "redirect:/inicio";
        }
        
    }
    
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, String titulo, String cuerpo, ModelMap modelo) throws ErrorServicio{
        try{
            noticiaServicio.eliminar(id);
        
            return "redirect:/inicio";
        }catch(ErrorServicio ex){
            modelo.put("error", ex.getMessage());
            return "redirect:/inicio";
        }
    }
    
    
}

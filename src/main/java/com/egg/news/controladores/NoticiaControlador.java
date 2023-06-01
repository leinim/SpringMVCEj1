package com.egg.news.controladores;

import com.egg.news.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/noticia")
public class NoticiaControlador {    
    /*Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias para operar
con la vista del usuario diseñada para la gestión de noticias (guardar/modificar noticia, listar
noticias, dar de baja, etc).*/
    
    @Autowired
    private NoticiaServicio noticiaServicio;
    
    @GetMapping("/{id}")
    public String noticia(@PathVariable String id, ModelMap modelo){
        
        modelo.put("noticia", noticiaServicio.getOne(id));
        return "noticia.html";        
    } 
    
    
        
    
}

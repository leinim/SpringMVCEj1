package com.egg.news.controladores;

import com.egg.news.entidades.Noticia;
import com.egg.news.errores.ErrorServicio;
import com.egg.news.servicios.NoticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @Autowired
    private NoticiaServicio noticiaServicio;    

    @GetMapping("/inicio")
    public String index(ModelMap modelo){
        try {
            List<Noticia> noticias = noticiaServicio.listarNoticias();
            
            modelo.addAttribute("noticias", noticias);
            
            return "index.html";
        } catch (ErrorServicio ex) {
            modelo.put("error", ex.getMessage());
            return "index.html";
        }
    }    
    
}

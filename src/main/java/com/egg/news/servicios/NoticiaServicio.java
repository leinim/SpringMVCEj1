package com.egg.news.servicios;

import com.egg.news.entidades.Noticia;
import com.egg.news.errores.ErrorServicio;
import com.egg.news.repositorios.NoticiaRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticiaServicio {
    //consulta, creación, modificación y dar de baja
    
    @Autowired
    private NoticiaRepositorio noticiaRepositorio;
    
    @Transactional
    public void crear(String titulo, String cuerpo) throws ErrorServicio{
        
        validar(titulo, cuerpo);
                
        Noticia noticia = new Noticia();
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        noticia.setFecha(new Date());
        
        noticiaRepositorio.save(noticia);        
    }
    
    @Transactional
    public void modificar(String id, String titulo, String cuerpo) throws ErrorServicio{
        
        validar(titulo, cuerpo);
        
        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);
        if (respuesta.isPresent()){
            Noticia noticia = respuesta.get();
            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);
            noticia.setFecha(new Date());
        
            noticiaRepositorio.save(noticia); 
        } else {
            throw new ErrorServicio("No se encontró la noticia solicitada.");
        }
    }
    
    @Transactional
    public void eliminar(String id) throws ErrorServicio{
        
        Optional<Noticia> noticia = noticiaRepositorio.findById(id);
        if (noticia.isPresent()){           
            noticiaRepositorio.deleteById(id);
        } else {
            throw new ErrorServicio("No se encontró la noticia solicitada.");
        }
        
    }
    
    private void validar(String titulo, String cuerpo) throws ErrorServicio {
        
        if (titulo == null || titulo.isEmpty()){
            throw new ErrorServicio("El título de la noticia no puede ser nulo.");
        }
        if (cuerpo == null || cuerpo.isEmpty()){
            throw new ErrorServicio("El cuerpo de la noticia no puede ser nulo.");
        }
    }
    
    public Noticia buscarPorTitulo(String titulo) throws ErrorServicio{
        Noticia noticia = noticiaRepositorio.buscarPorTitulo(titulo);
        if (noticia != null){           
            return noticia;
        } else {
            throw new ErrorServicio("No se encontró la noticia solicitada.");
        }
        
    }
    
    public List<Noticia> listarNoticias() throws ErrorServicio{
        List<Noticia> noticias = noticiaRepositorio.buscarTodo();
        
        return noticias;
    }
    
    public Noticia getOne(String id){
        return noticiaRepositorio.getOne(id);
    }
}

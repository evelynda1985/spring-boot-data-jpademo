package com.evecodeideas.com.springboot.app.controllers;

import com.evecodeideas.com.springboot.app.models.dao.IClienteDao;
import com.evecodeideas.com.springboot.app.models.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class ClienteController {

    @Autowired
    private IClienteDao clienteDao;

    @GetMapping("/listar")
    public String listar(Model model){

        model.addAttribute("titulo","Listado de clientes");
        model.addAttribute("clientes", clienteDao.findAll());

        return "listar";
    }

    @GetMapping("/form")
    public String crear(Map<String, Object> model){

        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        model.put("titulo", "Formulario");

        return "form";
    }

    @PostMapping("/form")
    //@Valid Cliente cliente, BindingResult result SIEMPRE TIENE QUE IR JUNTOS
    //BindingResult para mostrar el error
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model){

        if(cliente.getNombre() == null){
            System.out.println("===============================>Nullllllllllll");
        }
        if(cliente.getNombre().isEmpty()){
            System.out.println("<+++++++++++++++++++++++++++++Emptyyyyyyyyyy");
        }
        if(result.hasErrors()){
            model.addAttribute("titulo", "Formulario");
            return "form";
        }

        clienteDao.save(cliente);
        return "redirect:listar";
    }

}

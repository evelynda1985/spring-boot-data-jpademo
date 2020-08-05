package com.evecodeideas.com.springboot.app.controllers;

import com.evecodeideas.com.springboot.app.models.dao.IClienteDao;
import com.evecodeideas.com.springboot.app.models.entity.Cliente;
import com.evecodeideas.com.springboot.app.models.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Map;


@Controller
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
//    private IClienteDao clienteDao;
    private IClienteService clienteService;

    @GetMapping("/listar")
    public String listar(Model model){

        model.addAttribute("titulo","Listado de clientes");
        model.addAttribute("clientes", clienteService.findAll());

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
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status){

        if(result.hasErrors()){
            System.out.println("<+++++++++++++++++++++++++++++Emptyyyyyyyyyy");
            model.addAttribute("titulo", "Formulario");
            return "form";
        }

        clienteService.save(cliente);
        status.setComplete();
        return "redirect:listar";
    }

    @GetMapping("/form/{id}")
    public String edita(@PathVariable(value="id") Long id, Map<String, Object> model){

        Cliente cliente = null;

        if(id > 0){
            cliente = clienteService.findOne(id);
        }else{
            return "redirect:/listar";
        }

        model.put("cliente", cliente);
        model.put("titulo", "Editar Cliente");

        return "form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value="id") Long id){

        if(id > 0){
            clienteService.delete(id);
        }
        return "redirect:/listar";
    }

}

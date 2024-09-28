package pe.edu.cibertec.EvaluacionT1DAW_Mancha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import pe.edu.cibertec.EvaluacionT1DAW_Mancha.dto.PlacaRequestDTO;
import pe.edu.cibertec.EvaluacionT1DAW_Mancha.dto.PlacaResponseDTO;
import pe.edu.cibertec.EvaluacionT1DAW_Mancha.viewmodel.PlacaModel;

@Controller
@RequestMapping("/Consultar")
public class ConsultaPlacaController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/inicioplaca")
    public String inicioplaca(Model model) {
        PlacaModel placaModel = new PlacaModel("01", "", "");
        model.addAttribute("placamodel", placaModel);

        return "iniciandoplaca";
    }

    @PostMapping("/placa")
    public String consultarPlaca(
            @RequestParam("placa") String placa,
            Model model) {


        if (placa == null || placa.trim().isEmpty()) {
            PlacaModel placaModel = new PlacaModel("01", "Error: Debe ingresar una placa válida", "");
            model.addAttribute("placamodel", placaModel);
            return "iniciandoplaca";
        }

        try {

            String endpoint = "http://localhost:8081/consultar/placa";
            PlacaRequestDTO placaRequestDTO = new PlacaRequestDTO(placa);
            PlacaResponseDTO placaResponseDTO = restTemplate.postForObject(endpoint, placaRequestDTO, PlacaResponseDTO.class);


            if (placaResponseDTO != null) {
                model.addAttribute("placaResponse", placaResponseDTO);
                return "resultadoConsulta";
            } else {
                PlacaModel placaModel = new PlacaModel("02", "Error: No se encontró la placa", "");
                model.addAttribute("placamodel", placaModel);
                return "iniciandoplaca";
            }

        } catch (Exception e) {
            PlacaModel placaModel = new PlacaModel("99", "Error en la consulta de la placa", "");
            model.addAttribute("placamodel", placaModel);
            return "iniciandoplaca";
        }
    }
}

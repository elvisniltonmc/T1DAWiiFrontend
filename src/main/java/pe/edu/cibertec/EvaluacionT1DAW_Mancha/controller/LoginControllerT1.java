package pe.edu.cibertec.EvaluacionT1DAW_Mancha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.cibertec.EvaluacionT1DAW_Mancha.viewmodel.LoginModel;

@Controller /* para manejar páginas web y devuelve vistas como HTML(como GET o POST).*/
@RequestMapping("/login")
public class LoginControllerT1 {

    @GetMapping("/inicio")
    public String inicio(Model model) {
        LoginModel loginModel = new LoginModel("01", "", "");
        model.addAttribute("loginmodel", loginModel);

        return "iniciando";
    }

    @PostMapping("/autenticando")
    public String autenticando(
            @RequestParam("tipoDocumento") String tipoDocumento,
            @RequestParam("NumeroDocumento") String NumeroDocumento,
            @RequestParam("Password") String Password,
            Model model) {

        try {

            if (tipoDocumento == null || tipoDocumento.trim().length() == 0 ||
                    NumeroDocumento == null || NumeroDocumento.trim().length() == 0 ||
                    Password == null || Password.trim().length() == 0) {

                LoginModel loginModel = new LoginModel("01", "Error: Debe completar bien sus credenciales", "Son goku");
                model.addAttribute("loginmodel", loginModel);
                return "iniciando";
            }

            // Si la validación es exitosa
            LoginModel loginModel = new LoginModel("00", "", "Cesar Santos");
            model.addAttribute("loginmodel", loginModel);
            return "home";
        } catch (Exception e) {
            // Manejo del error
            model.addAttribute("errorMessage", "Ocurrió un error inesperado: " + e.getMessage());
            return "error";
        }
    }

}

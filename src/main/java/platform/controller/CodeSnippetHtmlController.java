package platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import platform.dto.CodeSnippetDTO;
import platform.dto.CodeSnippetIdDTO;
import platform.service.CodeSnippetService;

@Controller
@RequestMapping("/code")
public class CodeSnippetHtmlController {

    private final CodeSnippetService service;

    @Autowired
    public CodeSnippetHtmlController(CodeSnippetService service) {
        this.service = service;
    }

    @RequestMapping(path = "{uuid}")
    public String getCodeSnippetById(Model model, @PathVariable String uuid) {
        var codeSnippetIdDTO = new CodeSnippetIdDTO(uuid);
        var codeSnippetDTO = service.getCodeSnippetById(codeSnippetIdDTO);
        model.addAttribute("snippet", codeSnippetDTO);
        return "code";
    }

    @GetMapping(path = "latest")
    public String getLatest10CodeSnippets(Model model) {
        List<CodeSnippetDTO> codeSnippetDTOS = service.getLatest10CodeSnippets();
        model.addAttribute("snippets", codeSnippetDTOS);
        return "latest";
    }

    @GetMapping("new")
    public String createCodeSnippet() {
        return "create";
    }

}

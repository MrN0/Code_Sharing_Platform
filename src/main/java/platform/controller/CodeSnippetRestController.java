package platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import platform.dto.CodeSnippetDTO;
import platform.dto.CodeSnippetIdDTO;
import platform.exception.WrongArgumentException;
import platform.service.CodeSnippetService;

@RestController
@RequestMapping("/api/code")
public class CodeSnippetRestController {

    private final CodeSnippetService service;

    @Autowired
    public CodeSnippetRestController(CodeSnippetService service) {
        this.service = service;
    }

    @GetMapping(path = "{uuid}")
    public CodeSnippetDTO getCodeSnippetById(@PathVariable String uuid) {
        var codeSnippetIdDTO = new CodeSnippetIdDTO(uuid);
        return service.getCodeSnippetById(codeSnippetIdDTO);
    }

    @GetMapping(path = "latest")
    public List<CodeSnippetDTO> getLatest10CodeSnippets() {
        return service.getLatest10CodeSnippets();
    }

    @PostMapping(path = "new", consumes = "application/json")
    public CodeSnippetIdDTO postCodeSnippet(@RequestBody CodeSnippetDTO codeSnippetDTO) {
        if (codeSnippetDTO.getCode() == null) {
            throw new WrongArgumentException("Missing code!");
        }
        return service.postCodeSnippet(codeSnippetDTO);
    }

}

package training.vizsgaremekrecipes.creator;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creators")
@AllArgsConstructor
public class CreatorController {

    private CreatorService creatorService;


    @GetMapping
    public List<CreatorDto> findAllCreator() {
        return creatorService.findAllCreator();
    }


    @GetMapping("/{id}")
    public CreatorDto findCreatorById(@PathVariable long id) {
        return creatorService.findCreatorById(id);
    }


    @PostMapping
    public CreatorDto createCreator(@RequestBody CreateCreatorCommand command) {
        return creatorService.createCreator(command);
    }


    @PutMapping("/{id}")
    public CreatorDto updateCreatorById(@PathVariable long id, @RequestBody UpdateCreatorCommand command) {
        return creatorService.updateCreatorById(id, command);
    }


    @DeleteMapping("/{id}")
    public void deleteCreatorById(@PathVariable long id) {
        creatorService.deleteCreatorById(id);
    }
}


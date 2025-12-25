 @RestController
@RequestMapping("/api/categories")
@Tag(name = "Categories Endpoints")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public Category create(@RequestBody Category c) {
        return service.createCategory(c);
    }

    @GetMapping
    public List<Category> all() {
        return service.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category get(@PathVariable Long id) {
        return service.getCategory(id);
    }
}

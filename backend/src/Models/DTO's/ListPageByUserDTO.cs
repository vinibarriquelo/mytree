namespace backend.Models.DTO_s;

public class ListPageByUserDTO
{
    public Guid Id { get; set; }
    public string Title { get; set; }
    public string Description { get; set; }
    
    public ListPageByUserDTO(Guid id, string title, string description)
    {
        Id = id;
        Title = title;
        Description = description;
    }
}
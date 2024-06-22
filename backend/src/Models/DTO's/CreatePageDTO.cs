namespace backend.Models.DTO_s;

public class CreatePageDTO
{
    public string Title { get;  set; }
    
    public string Description { get;  set; }
    
    public string? Picture { get;  set; }
    
    public Guid userId { get; set; }
}
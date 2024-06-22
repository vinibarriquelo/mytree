using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace backend.Models;

public class Page
{
    [Key]
    public Guid Id { get;  set; }
    
    public string Title { get;  set; }
    
    public string Description { get;  set; }
    
    public string Picture { get;  set; }
    
    [ForeignKey("UserId")]
    public User User { get; set; }
    
    public List<Link> Links { get; set; } = new List<Link>();
    
    public Page(){}
    public Page(string title, string description, string picture, User user)
    {
        Title = title;
        Description = description;
        Picture = picture;
        User = user;
    }
}
using backend.Models;
using backend.Models.DTO_s;
using database;
using Microsoft.EntityFrameworkCore;

namespace backend.Repositories;

public class PageRepository
{
    private AppDbContext _context;

    public PageRepository(AppDbContext context)
    {
        _context = context;
    }

    public async Task CreatePage(Page page)
    {
        await _context.Pages.AddAsync(page);
        await _context.SaveChangesAsync();
    }

    public async Task<List<ListPageByUserDTO>> FindPagesByUserId(Guid userId)
    {
        var pages = await _context.Pages
            .Where(p => p.User.Id == userId)
            .Select(p => new ListPageByUserDTO(p.Id, p.Title, p.Description))
            .ToListAsync();

        return pages;
    }

    public async Task<Page> FindPageById(Guid pageId)
    { 
        var page = await _context.Pages.FindAsync(pageId);
        return page;
    }
    
    public async Task UpdatePage(Page page)
    {
        _context.Pages.Update(page);
        await _context.SaveChangesAsync();
    }

    public async Task DeletePage(Page page)
    {
        _context.Pages.Remove(page);
        await _context.SaveChangesAsync();
    }
}
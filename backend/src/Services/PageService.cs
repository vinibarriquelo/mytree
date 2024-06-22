using System.Net;
using backend.Models;
using backend.Models.DTO_s;
using backend.Repositories;

namespace backend.Services;

public class PageService
{
    private PageRepository _pageRepository;
    private UserService _userService;

    public PageService(PageRepository pageRepository, UserService userService)
    {
        _pageRepository = pageRepository;
        _userService = userService;
    }

    public async Task<Page> CreatePage(CreatePageDTO payload)
    {
        var user = await _userService.FindUserById(payload.userId);

        if (user == null)
            throw new HttpException(HttpStatusCode.BadRequest, "Não encontramos esse usuário!");

        var page = new Page(payload.Title, payload.Description, payload.Picture, user);
        
        await _pageRepository.CreatePage(page);
        
        return page;
    }

    public async Task<List<ListPageByUserDTO>> FindPagesByUserId(Guid userId)
    {
        var user = await _userService.FindUserById(userId);
        
        if (user == null)
            throw new HttpException(HttpStatusCode.BadRequest, "Não encontramos esse usuário!");

        var pages = await _pageRepository.FindPagesByUserId(userId);
        return pages;
    }

    public async Task<Page> UpdatePage(EditPageDTO payload, Guid pageId)
    {
        var page = await _pageRepository.FindPageById(pageId);

        if (page == null)
            throw new HttpException(HttpStatusCode.BadRequest, "Página não localizada!");

        page.Description = payload.Description;
        page.Title = payload.Title;
        page.Picture = payload.Picture;

        await _pageRepository.UpdatePage(page);

        return page;
    }

    public async Task DeletePage(Guid pageId)
    {
        var page = await _pageRepository.FindPageById(pageId);
        
        if (page == null)
            throw new HttpException(HttpStatusCode.BadRequest, "Página não localizada!");

        await _pageRepository.DeletePage(page);
    }
}
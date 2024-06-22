using backend.Models.DTO_s;
using FluentValidation;

namespace backend.Models.Validator;

public class CreatePageDTOValidator : AbstractValidator<CreatePageDTO>
{
    public CreatePageDTOValidator()
    {
        RuleFor(page => page.Title)
            .NotEmpty().WithMessage("É preciso informar o título!")
            .MinimumLength(5).WithMessage("O título precisa ter no mínimo 5 caracteres.");
        
        RuleFor(page => page.Description)
            .NotEmpty()
            .WithMessage("É preciso informar a descrição");

        RuleFor(page => page.userId)
            .NotEmpty()
            .WithMessage("É preciso informar o usuario dono da página!");



    }
}
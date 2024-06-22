using backend.Models.DTO_s;
using FluentValidation;

namespace backend.Models.Validator;

public class EditPageDTOValidator : AbstractValidator<EditPageDTO>
{
    public EditPageDTOValidator()
    {
        RuleFor(page => page.Title)
            .NotEmpty().WithMessage("É preciso informar o título!")
            .MinimumLength(5).WithMessage("O título precisa ter no mínimo 5 caracteres.");

        RuleFor(page => page.Description)
            .NotEmpty()
            .WithMessage("É preciso informar a descrição");
    }
}
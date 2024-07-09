import { Component, ElementRef, ViewChild } from '@angular/core';

@Component({
  selector: 'app-post-box',
  templateUrl: './post-box.component.html',
  styleUrl: './post-box.component.css'
})
export class PostBoxComponent {

  @ViewChild('myTextarea') myTextarea: ElementRef<HTMLTextAreaElement> | undefined;

  ajustarAltura(): void {
    const textarea = this.myTextarea?.nativeElement;
    if (textarea) {
      textarea.style.maxHeight = 'none'; //Remove o limite de altura
      textarea.style.height = 'auto'; // Reset altura para auto
      const alturaMinima = '50px'; // Altura mínima desejada

      // Calcula a altura necessária baseada no conteúdo
      textarea.style.height = `${Math.max(textarea.scrollHeight, parseInt(alturaMinima))}px`;

      // Verifica se o conteúdo da textarea está vazio
      if (textarea.value.trim() === '') {
        textarea.style.height = alturaMinima; // Volta para a altura mínima
      }
    }
  }

  submitted = false;
}

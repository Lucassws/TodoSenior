import { TodoService } from '../../services/todo.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Todo } from '../../models/todo';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {
  todo: Todo = {
    title: '',
    description: '',
    completionDate: null,
    status : ''
  }

  constructor(private router: Router, private service: TodoService, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
  }

  create(): void {
    if (!this.todo.title.trim()) {
      this.snackBar.open('O campo "Titulo" é obrigatório', 'Fechar', {
        duration: 3000,
      });
      return;
    }

    this.todo.status = 'PENDING';
    this.service.create(this.todo).subscribe((resposta) => {
      this.service.message('To-do criado com sucesso!');
      this.router.navigate(['']);
    },err => {
      this.service.message('Falha ao criar o TO-DO');
      this.router.navigate(['']);
    })
  }

  cancel(): void {
    this.router.navigate(['']);
  }

}

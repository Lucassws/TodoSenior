import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Todo } from '../../models/todo';
import { TodoService } from '../../services/todo.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {
  todo: Todo = {
    title: '',
    description: '',
    completionDate: null,
    status : ''
  }

  constructor(private router: Router, private service: TodoService, private route: ActivatedRoute, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get("id");
    if (id !== null) {
      this.todo.id = +id; // Convert string to number
      this.findById();
    }
  }

  findById(): void {
    if (this.todo.id) {
      this.service.findById(this.todo.id).subscribe((todo) => {
        this.todo = todo;
      });
    }
  }

  update(): void {
    if (!this.todo.title.trim()) {
      this.snackBar.open('O campo "Titulo" é obrigatório', 'Fechar', {
        duration: 3000,
      });
      return;
    }

    this.service.update(this.todo).subscribe((resposta) => {
      this.service.message('Informações atualizadas com sucesso!')
      this.router.navigate(['']);
    }, err => {
      this.service.message('Falha ao atualizar o TO-DO!')
      this.router.navigate([''])
    })
  }
  
  cancel(): void {
    this.router.navigate(['']);
  }

  formatarData(): void {
    let data = new Date(this.todo.completionDate)
    this.todo.completionDate = `${data.getDate()}/${data.getMonth() +1 }/${data.getFullYear()}`
  }
}

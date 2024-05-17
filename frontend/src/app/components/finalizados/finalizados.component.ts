import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Todo } from '../../models/todo';
import { TodoService } from '../../services/todo.service';

@Component({
  selector: 'app-finalizados',
  templateUrl: './finalizados.component.html',
  styleUrls: ['./finalizados.component.css']
})
export class FinalizadosComponent implements OnInit {

  listFinished: Todo[] = [];

  constructor(private service: TodoService, private router: Router) { }

  ngOnInit(): void {
    this.findAll();
    
  }


  findAll(): void {
    this.service.findAll().subscribe((resposta) =>{
      resposta.forEach(todo => {
        if(todo.status == 'COMPLETED'){
          this.listFinished.push(todo);
        }        
      })
    })
  }
  voltar(): void {
    this.router.navigate(['']);
  }

}

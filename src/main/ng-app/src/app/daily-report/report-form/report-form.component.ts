import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { Http, URLSearchParams, RequestOptions, Headers } from "@angular/http";
import { Report } from "../report";
import { FormGroup } from "@angular/forms";
import { environment } from "../../../environments/environment";

@Component({
  selector: 'report-form',
  templateUrl: './report-form.component.html',
  styleUrls: ['./report-form.component.css']
})
export class ReportForm implements OnInit {

  report = new Report()
  @Output() formSubmit = new EventEmitter<String>();

  constructor(private http: Http) { }

  ngOnInit() {
  }

  onSubmit(f: FormGroup) {
    if (f.valid) {
      this.submitForm()      
    } else {
      for (let i in f.controls) { 
        f.controls[i].markAsDirty()
      }
      alert('Invalid data, please fill all required fields')
    }
  }

  private submitForm() {
    let params = new URLSearchParams()
    for (let key in this.report) {
      if (this.report.hasOwnProperty(key)) {
        params.set(key, this.report[key])
      }
    }
    
    let opts = new RequestOptions();
    let h = new Headers()
    h.append('Content-Type', 'application/x-www-form-urlencoded')
    opts.headers = h
    
    this.http.post(environment.apiUrl + '/report', params, opts).subscribe(
      res => {
        this.formSubmit.emit(res.json()['message'])
      }
    );
  }
}

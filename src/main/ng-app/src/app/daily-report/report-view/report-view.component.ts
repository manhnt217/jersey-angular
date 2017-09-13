import { Component, OnInit, Input } from '@angular/core';
import { Report } from "../report";
import { Http } from "@angular/http";
import { environment } from "../../../environments/environment";
import { Subject } from "rxjs/Subject";

@Component({
  selector: 'report-view',
  templateUrl: './report-view.component.html',
  styleUrls: ['./report-view.component.css']
})
export class ReportViewComponent implements OnInit {

  reports: Report[];
  @Input() formSubmitted: Subject<String>;

  constructor(private http: Http) { }

  ngOnInit() {
    this.reports = new Array<Report>();
    if (this.formSubmitted) {
      this.formSubmitted.subscribe(result => {
        // if (result == 'success') {
          this.load()
        // }
      })
    }

    this.load()
  }

  load() {
    this.http.get(environment.apiUrl + '/report').subscribe(res => {
      let rs = res.json()
      this.deserialize(rs)
    });
  }

  private deserialize(json) {
    this.reports = [];
    for(let r of json) {
      let report = new Report();
      report.copy(r);
      this.reports.unshift(report);
    }
  }

  ngOnDestroy() {
    if (this.formSubmitted) {
      
      // needed if child gets re-created (eg on some model changes)
      // note that subsequent subscriptions on the same subject will fail
      // so the parent has to re-create parentSubject on changes
      this.formSubmitted.unsubscribe();
    }
  }
}

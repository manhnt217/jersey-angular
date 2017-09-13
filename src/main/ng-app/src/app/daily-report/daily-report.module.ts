import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReportForm } from "./report-form/report-form.component";
import { FormsModule } from '@angular/forms';
import  { HttpModule } from '@angular/http';
import { ReportViewComponent } from './report-view/report-view.component';
import { ReportComponent } from './report/report.component'

@NgModule({
  imports: [
    CommonModule, FormsModule, HttpModule
  ],
  declarations: [ReportForm, ReportViewComponent, ReportComponent],
  bootstrap: [ReportComponent],
  exports: [ReportComponent, ReportViewComponent]
})
export class DailyReportModule { }

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { DailyReportModule } from "./daily-report/daily-report.module";
import { ReportComponent } from "./daily-report/report/report.component";
import { Routes, RouterModule } from "@angular/router";
import { HomeComponent } from './home/home.component';
import { AppComponent } from "./app.component";
import { ReportViewComponent } from "./daily-report/report-view/report-view.component";

const routes: Routes = [
  { path: 'report', component: ReportComponent },
  { path: 'report/view', component: ReportViewComponent },
  { path: '', component: HomeComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent],
  imports: [
    BrowserModule, DailyReportModule, RouterModule.forRoot(routes, { useHash: true })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

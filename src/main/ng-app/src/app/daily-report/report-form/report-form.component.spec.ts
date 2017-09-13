import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportForm } from './report-form.component';

describe('ReportForm', () => {
  let component: ReportForm;
  let fixture: ComponentFixture<ReportForm>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReportForm ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportForm);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LogDisplayComponent } from './log-display.component';

describe('LogDisplayComponent', () => {
  let component: LogDisplayComponent;
  let fixture: ComponentFixture<LogDisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LogDisplayComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LogDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

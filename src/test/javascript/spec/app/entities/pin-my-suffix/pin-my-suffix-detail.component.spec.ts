/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { PINMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/pin-my-suffix/pin-my-suffix-detail.component';
import { PINMySuffixService } from '../../../../../../main/webapp/app/entities/pin-my-suffix/pin-my-suffix.service';
import { PINMySuffix } from '../../../../../../main/webapp/app/entities/pin-my-suffix/pin-my-suffix.model';

describe('Component Tests', () => {

    describe('PINMySuffix Management Detail Component', () => {
        let comp: PINMySuffixDetailComponent;
        let fixture: ComponentFixture<PINMySuffixDetailComponent>;
        let service: PINMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [PINMySuffixDetailComponent],
                providers: [
                    PINMySuffixService
                ]
            })
            .overrideTemplate(PINMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PINMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PINMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new PINMySuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.pIN).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});

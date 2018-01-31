/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { PINMySuffixComponent } from '../../../../../../main/webapp/app/entities/pin-my-suffix/pin-my-suffix.component';
import { PINMySuffixService } from '../../../../../../main/webapp/app/entities/pin-my-suffix/pin-my-suffix.service';
import { PINMySuffix } from '../../../../../../main/webapp/app/entities/pin-my-suffix/pin-my-suffix.model';

describe('Component Tests', () => {

    describe('PINMySuffix Management Component', () => {
        let comp: PINMySuffixComponent;
        let fixture: ComponentFixture<PINMySuffixComponent>;
        let service: PINMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [PINMySuffixComponent],
                providers: [
                    PINMySuffixService
                ]
            })
            .overrideTemplate(PINMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PINMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PINMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new PINMySuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.pINS[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});

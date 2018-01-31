import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { PINMySuffix } from './pin-my-suffix.model';
import { PINMySuffixPopupService } from './pin-my-suffix-popup.service';
import { PINMySuffixService } from './pin-my-suffix.service';

@Component({
    selector: 'jhi-pin-my-suffix-dialog',
    templateUrl: './pin-my-suffix-dialog.component.html'
})
export class PINMySuffixDialogComponent implements OnInit {

    pIN: PINMySuffix;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private pINService: PINMySuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.pIN.id !== undefined) {
            this.subscribeToSaveResponse(
                this.pINService.update(this.pIN));
        } else {
            this.subscribeToSaveResponse(
                this.pINService.create(this.pIN));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<PINMySuffix>>) {
        result.subscribe((res: HttpResponse<PINMySuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: PINMySuffix) {
        this.eventManager.broadcast({ name: 'pINListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-pin-my-suffix-popup',
    template: ''
})
export class PINMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private pINPopupService: PINMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.pINPopupService
                    .open(PINMySuffixDialogComponent as Component, params['id']);
            } else {
                this.pINPopupService
                    .open(PINMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

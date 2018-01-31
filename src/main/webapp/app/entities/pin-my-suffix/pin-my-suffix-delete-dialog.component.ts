import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { PINMySuffix } from './pin-my-suffix.model';
import { PINMySuffixPopupService } from './pin-my-suffix-popup.service';
import { PINMySuffixService } from './pin-my-suffix.service';

@Component({
    selector: 'jhi-pin-my-suffix-delete-dialog',
    templateUrl: './pin-my-suffix-delete-dialog.component.html'
})
export class PINMySuffixDeleteDialogComponent {

    pIN: PINMySuffix;

    constructor(
        private pINService: PINMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.pINService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'pINListModification',
                content: 'Deleted an pIN'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-pin-my-suffix-delete-popup',
    template: ''
})
export class PINMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private pINPopupService: PINMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.pINPopupService
                .open(PINMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

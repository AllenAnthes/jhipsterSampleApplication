import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { PINMySuffix } from './pin-my-suffix.model';
import { PINMySuffixService } from './pin-my-suffix.service';

@Component({
    selector: 'jhi-pin-my-suffix-detail',
    templateUrl: './pin-my-suffix-detail.component.html'
})
export class PINMySuffixDetailComponent implements OnInit, OnDestroy {

    pIN: PINMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private pINService: PINMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPINS();
    }

    load(id) {
        this.pINService.find(id)
            .subscribe((pINResponse: HttpResponse<PINMySuffix>) => {
                this.pIN = pINResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPINS() {
        this.eventSubscriber = this.eventManager.subscribe(
            'pINListModification',
            (response) => this.load(this.pIN.id)
        );
    }
}

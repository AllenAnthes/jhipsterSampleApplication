import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { PINMySuffix } from './pin-my-suffix.model';
import { PINMySuffixService } from './pin-my-suffix.service';

@Injectable()
export class PINMySuffixPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private pINService: PINMySuffixService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.pINService.find(id)
                    .subscribe((pINResponse: HttpResponse<PINMySuffix>) => {
                        const pIN: PINMySuffix = pINResponse.body;
                        pIN.createtimestamp = this.datePipe
                            .transform(pIN.createtimestamp, 'yyyy-MM-ddTHH:mm:ss');
                        pIN.expiretimestamp = this.datePipe
                            .transform(pIN.expiretimestamp, 'yyyy-MM-ddTHH:mm:ss');
                        pIN.claimtimestamp = this.datePipe
                            .transform(pIN.claimtimestamp, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.pINModalRef(component, pIN);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.pINModalRef(component, new PINMySuffix());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    pINModalRef(component: Component, pIN: PINMySuffix): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.pIN = pIN;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}

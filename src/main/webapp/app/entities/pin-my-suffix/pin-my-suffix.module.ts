import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    PINMySuffixService,
    PINMySuffixPopupService,
    PINMySuffixComponent,
    PINMySuffixDetailComponent,
    PINMySuffixDialogComponent,
    PINMySuffixPopupComponent,
    PINMySuffixDeletePopupComponent,
    PINMySuffixDeleteDialogComponent,
    pINRoute,
    pINPopupRoute,
} from './';

const ENTITY_STATES = [
    ...pINRoute,
    ...pINPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        PINMySuffixComponent,
        PINMySuffixDetailComponent,
        PINMySuffixDialogComponent,
        PINMySuffixDeleteDialogComponent,
        PINMySuffixPopupComponent,
        PINMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        PINMySuffixComponent,
        PINMySuffixDialogComponent,
        PINMySuffixPopupComponent,
        PINMySuffixDeleteDialogComponent,
        PINMySuffixDeletePopupComponent,
    ],
    providers: [
        PINMySuffixService,
        PINMySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationPINMySuffixModule {}

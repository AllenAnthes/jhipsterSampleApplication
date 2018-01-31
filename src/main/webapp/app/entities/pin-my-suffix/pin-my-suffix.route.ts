import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PINMySuffixComponent } from './pin-my-suffix.component';
import { PINMySuffixDetailComponent } from './pin-my-suffix-detail.component';
import { PINMySuffixPopupComponent } from './pin-my-suffix-dialog.component';
import { PINMySuffixDeletePopupComponent } from './pin-my-suffix-delete-dialog.component';

export const pINRoute: Routes = [
    {
        path: 'pin-my-suffix',
        component: PINMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PINS'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'pin-my-suffix/:id',
        component: PINMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PINS'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const pINPopupRoute: Routes = [
    {
        path: 'pin-my-suffix-new',
        component: PINMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PINS'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'pin-my-suffix/:id/edit',
        component: PINMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PINS'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'pin-my-suffix/:id/delete',
        component: PINMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PINS'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

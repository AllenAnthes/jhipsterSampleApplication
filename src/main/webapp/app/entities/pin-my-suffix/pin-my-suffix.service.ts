import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { PINMySuffix } from './pin-my-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<PINMySuffix>;

@Injectable()
export class PINMySuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/pins';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(pIN: PINMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(pIN);
        return this.http.post<PINMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(pIN: PINMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(pIN);
        return this.http.put<PINMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<PINMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<PINMySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<PINMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<PINMySuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: PINMySuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<PINMySuffix[]>): HttpResponse<PINMySuffix[]> {
        const jsonResponse: PINMySuffix[] = res.body;
        const body: PINMySuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to PINMySuffix.
     */
    private convertItemFromServer(pIN: PINMySuffix): PINMySuffix {
        const copy: PINMySuffix = Object.assign({}, pIN);
        copy.createtimestamp = this.dateUtils
            .convertDateTimeFromServer(pIN.createtimestamp);
        copy.expiretimestamp = this.dateUtils
            .convertDateTimeFromServer(pIN.expiretimestamp);
        copy.claimtimestamp = this.dateUtils
            .convertDateTimeFromServer(pIN.claimtimestamp);
        return copy;
    }

    /**
     * Convert a PINMySuffix to a JSON which can be sent to the server.
     */
    private convert(pIN: PINMySuffix): PINMySuffix {
        const copy: PINMySuffix = Object.assign({}, pIN);

        copy.createtimestamp = this.dateUtils.toDate(pIN.createtimestamp);

        copy.expiretimestamp = this.dateUtils.toDate(pIN.expiretimestamp);

        copy.claimtimestamp = this.dateUtils.toDate(pIN.claimtimestamp);
        return copy;
    }
}

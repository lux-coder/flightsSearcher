import { Aircraft } from './aircraft';
import { Arrival } from './arrival';
import { Departure } from './departure';
import { Operating } from './operating';

export class Segments {
    departure: Departure;
    arrival: Arrival;
    carrierCode: string;
    numb: string;
    aircraft: Aircraft;
    operating: Operating;
    duration: string;
    id: string;
    numberOfStops: number;
    blacklistedInEU: boolean;
}

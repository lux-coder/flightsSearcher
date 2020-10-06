import { Itineraries } from './itineraries';
import { Price } from './price';

export class FlightData {
    type: string;
    id: number;
    source: string;
    instantTicketingRequired: boolean;
    nonHomogeneous: boolean;
    oneWay: boolean;
    lastTicketingDate: string;
    numberOfBookableSeats: number;
    itineraries: Itineraries[];
    price: Price;
}
